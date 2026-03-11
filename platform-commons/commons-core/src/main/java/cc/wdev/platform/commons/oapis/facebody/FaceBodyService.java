package cc.wdev.platform.commons.oapis.facebody;

import cc.wdev.platform.commons.oapis.facebody.model.FaceBodyResult;

/**
 * @author elvea
 */
public interface FaceBodyService {

    FaceBodyResult detectFace(String target);

    FaceBodyResult compareFace(String target, String source);

}
