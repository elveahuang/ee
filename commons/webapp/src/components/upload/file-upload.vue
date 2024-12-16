<template>
    <el-upload
        v-model:file-list="fileList"
        name="attachment"
        list-type="picture-card"
        :action="action"
        :multiple="multiple && attachment.attachmentType.multipleInd"
        :custom-request="uploadHandle"
        :before-upload="beforeUpload"
        :accept="`.${attachment.attachmentType?.ext.join(',.')}`"
        :on-reject="remove"
        @preview="handlePreview"
        @remove="remove"
        @change="handleChange"
    >
        <div v-if="allowUpload">
            <loading-outlined v-if="uploading" />
            <plus-outlined v-else />
            <div class="ant-upload-text">Upload</div>
        </div>
    </el-upload>
</template>
<script lang="ts" setup>
import { attachmentTypeSearchApi } from '@commons/core/api/admin/attachment-type.ts';
import { AttachmentApi } from '@commons/core/api/attachment.ts';
import env from '@commons/core/env.ts';
import { R } from '@commons/core/types';
import { AttachmentEntity, AttachmentFileEntity, AttachmentType } from '@commons/core/types/attachment.type.ts';
import { CusFile } from '@commons/core/types/upload.ts';
import { log } from '@commons/core/utils';
import { UploadProps } from 'element-plus';
import { isEmpty } from 'radash';
import { computed, onMounted } from 'vue';

const emits = defineEmits(['update:attachment']);
const props = defineProps({
    code: {
        type: String,
        required: true,
    },
    attachment: {
        type: Object,
        required: false,
        default: {
            targetId: 0 as number | string,
            targetType: '' as string,
            attachmentType: { multiple: false } as AttachmentType,
            fileList: [] as AttachmentFileEntity[],
        },
    },
    action: {
        type: String,
        required: false,
        default: env.server + '/api/v1/attachment/upload',
    },
    /* 支持上传文件夹 */
    directory: {
        type: Boolean,
        required: false,
        default: false,
    },
    /* 是否禁用 */
    disabled: {
        type: Boolean,
        required: false,
        default: false,
    },
    /* 文件选择框是否支持多选文件[ie10+ 支持] */
    multiple: {
        type: Boolean,
        required: false,
        default: false,
    },
    /* 支持最大上传数量 */
    maxCount: {
        type: Number,
        required: false,
        default: 99,
    },
    /* 附件大小最大限制, 单位MB */
    maxSize: {
        type: Number,
        required: false,
        default: 10240,
    },
});

// 上传中状态
const uploading = ref<boolean>(false);
const multiple = ref<boolean>(props.multiple);
// 附件类型
const attachment = reactive<AttachmentEntity>(props.attachment);

// 附件集合
const fileList = ref<UploadProps['fileList'] | Array<CusFile>>(
    (!isEmpty(attachment.fileList) && attachment.fileList?.map((file: AttachmentFileEntity) => JSON.parse(file.extra))) || [],
);
// 是否允许继续上传
const allowUpload = computed<boolean>(() => (multiple.value ? fileList.value.length < props.maxCount : fileList.value.length < 1));

/**
 * 上传前检查
 */
const beforeUpload = async (file: UploadProps['fileList'][number]) => {
    // 附件格式校验
    const typeFlag = attachment.attachmentType.fileTypes.includes(file?.type);
    // 附件大小校验
    const sizeFlag = file.size / 1024 / 1024 < props.maxSize;
    !typeFlag && message.error(`File type incorrect! should be inside [${attachment.attachmentType.fileTypes.join(', ')}]`);
    !sizeFlag && message.error(`Image must smaller than ${props.maxSize}MB!`);
    // 返回
    return typeFlag && sizeFlag;
};

const getBase64 = (img: Blob, callback: (base64Url: string) => void) => {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result as string));
    reader.readAsDataURL(img);
};
const handleChange = (info: UploadChangeParam) => {
    if (info.file.status === 'uploading') {
        uploading.value = true;
        return;
    }
    if (info.file.status === 'done') {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, (base64Url: string) => {
            uploading.value = false;
        });
    }
    if (info.file.status === 'error') {
        uploading.value = false;
        message.error('upload error');
    }
};

/**
 * 上传附件
 */
const uploadHandle = async (event: any) => {
    const formData = new FormData();
    formData.append('targetType', props.code);
    formData.append('attachmentType', props.code);
    formData.append(event.filename, event.file);

    if (event.data) {
        Object.keys(event.data).forEach((key) => {
            formData.append(key, event.data[key]);
        });
    }
    AttachmentApi.upload(formData)
        .then((res) => {
            event.onSuccess(res, event.file); //上传成功监听事件

            attachment.fileList = fileList.value.map((file: any) => {
                return {
                    ...file.response.data[0],
                    extra: JSON.stringify(file),
                };
            });

            emits('update:attachment', { ...attachment });
        })
        .catch((err) => {
            event.onError(err, err, event.file); //上传失败监听事件,onError 第二个参数要是字符串才能显示错误原因
            message.error(event.file.name + err);
        });
};

/**
 * 删除附件
 */
const remove = (file: CusFile): boolean => {
    attachment.fileList = fileList.value
        .filter((f: any) => file.uid !== f.uid)
        ?.map((file: any) => {
            return {
                ...file.response.data[0],
                extra: JSON.stringify(file),
            };
        });
    emits('update:attachment', { ...attachment });
    return true;
};

/**
 * 预览附件
 */
const handlePreview = (file: CusFile): void => {
    window.open(file.response.data[0].url);
};

onMounted(async () => {
    log('Component <<FileUpload>> mounted.');

    await attachmentTypeSearchApi({ code: props.code }).then((resp: R<AttachmentType>) => {
        attachment.targetType = resp.data?.code;
        attachment.attachmentType = { ...resp.data };
        multiple.value = attachment.attachmentType?.multipleInd;
        emits('update:attachment', { ...attachment });
    });
});
</script>
