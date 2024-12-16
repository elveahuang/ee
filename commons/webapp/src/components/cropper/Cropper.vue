<template>
    <div id="create-cropper-modal">
        <el-dialog
            :visible="visible"
            :title="options.title"
            :mask-closable="false"
            :confirm-loading="confirmLoading"
            :width="600"
            @cancel="handleCancel"
        >
            <a-row>
                <el-col :xs="24" :md="24" :style="{ height: '400px' }">
                    <van-uploader
                        ref="cropper"
                        :img="options.img"
                        :info="true"
                        :auto-crop="options.autoCrop"
                        :auto-crop-width="options.autoCropWidth"
                        :auto-crop-height="options.autoCropHeight"
                        :fixed-box="options.fixedBox"
                        :mode="cropperMode"
                        @real-time="realTime"
                    />
                </el-col>
                <!-- <el-col :xs="24" :md="12" :style="{ height: '250px' }">
<div
  :class="
    options.previewsCircle
      ? 'avatar-upload-preview'
      : 'avatar-upload-preview_range'
  "
>
  <img :src="previews.url" :style="previews.img" />
</div>
</el-col> -->
            </a-row>
            <template #footer>
                <el-button key="back" @click="handleCancel"> 取消 </el-button>
                <el-button key="submit" type="primary" :loading="confirmLoading" @click="handleSumbit"> 确定 </el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const props = defineProps({
    //图片存储在oss上的上级目录名
    imgType: {
        type: String,
        default: '',
    },
    fileName: {
        type: String, // 文件名
        default: '',
    },
    cropperMode: {
        // 图片渲染方式
        type: String,
        default: 'contain',
    },
    visible: {
        type: Boolean,
        default: false,
    },
    img: {
        type: Object,
        default: {},
    },
    confirmLoading: {
        type: Boolean,
        default: false,
    },
    options: {
        type: Object,
        default: {
            img: '', //裁剪图片的地址
            autoCrop: true, //是否默认生成截图框
            autoCropWidth: 200, //默认生成截图框宽度
            autoCropHeight: 200, //默认生成截图框高度
            fixedBox: true, //是否固定截图框大小 不允许改变
            previewsCircle: false, //预览图是否是原圆形
            title: '修改图片',
        },
    },
    previews: {
        type: Object,
        default: {},
    },
    url: {
        type: Object,
        default: {
            upload: '/sys/common/saveToImgByStr',
        },
    },
});

const visible = ref<boolean>(props.visible);
const edit = (record) => {
    this.visible = true;
    this.options = Object.assign({}, this.options, record);
};
/*export default {
    props: ,
    mounted() {},
    data() {
        return {
            visible: false,
            img: null,
            confirmLoading: false,
            options: {
                img: '', //裁剪图片的地址
                autoCrop: true, //是否默认生成截图框
                autoCropWidth: 200, //默认生成截图框宽度
                autoCropHeight: 200, //默认生成截图框高度
                fixedBox: true, //是否固定截图框大小 不允许改变
                previewsCircle: false, //预览图是否是原圆形
                title: '修改图片',
            },
            previews: {},
            url: {
                upload: '/sys/common/saveToImgByStr',
            },
        };
    },
    methods: {
        edit(record) {
            this.visible = true;
            this.options = Object.assign({}, this.options, record);
        },
        // 取消截图
        handleCancel() {
            this.confirmLoading = false;
            this.visible = false;
            this.$emit('cropper-no');
        },
        // 确认截图
        handleSumbit() {
            this.confirmLoading = true;
            // 获取截图的base64 数据
            this.$refs.cropper.getCropData(async (data) => {
                const file = this.dataURLtoFile(data, this.fileName);
                let formData = new window.FormData();
                formData.append('file', file);
                // 调用后端接口上传裁剪后图片，获取图片url传递给组件表单和回显
                // formData.append("token", $("#upload_token").val());
                // try {
                //   const { id, url } = await Upgrad.uploadImageApi(formData);
                //   this.$emit("cropper-ok", id, url);
                // } catch (error) {
                //   this.$message.error("网络异常，请稍后再试！");
                // } finally {
                //   this.visible = false;
                //   this.confirmLoading = false;
                // }
            });
        },
        //移动框的事件
        realTime(data) {
            this.previews = data;
        },
        // base 64 转成二进制文件流
        dataURLtoFile(dataurl, filename) {
            var arr = dataurl.split(','),
                mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]),
                n = bstr.length,
                u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new File([u8arr], filename, { type: mime });
        },
    },
};*/
</script>
