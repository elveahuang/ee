var javaCppVersion = "1.5.13"
var ffmpegVersion = "8.0.1-$javaCppVersion"
var tesseractVersion = "5.5.2-$javaCppVersion"
var leptonicaVersion = "1.87.0-$javaCppVersion"

dependencies {
    // javacv & javacpp
    api("org.bytedeco:javacv:$javaCppVersion") {
        exclude(group = "org.bytedeco", module = "artoolkitplus")
        exclude(group = "org.bytedeco", module = "flycapture")
        exclude(group = "org.bytedeco", module = "libdc1394")
        exclude(group = "org.bytedeco", module = "libfreenect")
        exclude(group = "org.bytedeco", module = "libfreenect2")
        exclude(group = "org.bytedeco", module = "librealsense")
        exclude(group = "org.bytedeco", module = "librealsense2")
        exclude(group = "org.bytedeco", module = "opencv")
        exclude(group = "org.bytedeco", module = "openblas")
        exclude(group = "org.bytedeco", module = "videoinput")
    }
    api(group = "org.bytedeco", name = "javacpp-platform", version = javaCppVersion) {
        exclude(group = "org.bytedeco", module = "javacpp")
    }
    api(group = "org.bytedeco", name = "javacpp", version = javaCppVersion)
    api(group = "org.bytedeco", name = "javacpp", version = javaCppVersion, classifier = "linux-x86_64")
    api(group = "org.bytedeco", name = "javacpp", version = javaCppVersion, classifier = "macosx-arm64")
    api(group = "org.bytedeco", name = "javacpp", version = javaCppVersion, classifier = "windows-x86_64")

    // ffmpeg
    api(group = "org.bytedeco", name = "ffmpeg-platform", version = ffmpegVersion) {
        exclude(group = "org.bytedeco", module = "ffmpeg")
        exclude(group = "org.bytedeco", module = "javacpp-platform")
    }
    api(group = "org.bytedeco", name = "ffmpeg", version = ffmpegVersion)
    api(group = "org.bytedeco", name = "ffmpeg", version = ffmpegVersion, classifier = "linux-x86_64")
    api(group = "org.bytedeco", name = "ffmpeg", version = ffmpegVersion, classifier = "macosx-arm64")
    api(group = "org.bytedeco", name = "ffmpeg", version = ffmpegVersion, classifier = "windows-x86_64")

    // tesseract
    api(group = "org.bytedeco", name = "tesseract-platform", version = tesseractVersion) {
        exclude(group = "org.bytedeco", module = "tesseract")
        exclude(group = "org.bytedeco", module = "javacpp-platform")
        exclude(group = "org.bytedeco", module = "leptonica-platform")
    }
    api(group = "org.bytedeco", name = "tesseract", version = tesseractVersion)
    api(group = "org.bytedeco", name = "tesseract", version = tesseractVersion, classifier = "linux-x86_64")
    api(group = "org.bytedeco", name = "tesseract", version = tesseractVersion, classifier = "macosx-arm64")
    api(group = "org.bytedeco", name = "tesseract", version = tesseractVersion, classifier = "windows-x86_64")

    // leptonica
    api(group = "org.bytedeco", name = "leptonica-platform", version = leptonicaVersion) {
        exclude(group = "org.bytedeco", module = "leptonica")
        exclude(group = "org.bytedeco", module = "javacpp-platform")
    }
    api(group = "org.bytedeco", name = "leptonica", version = leptonicaVersion)
    api(group = "org.bytedeco", name = "leptonica", version = leptonicaVersion, classifier = "linux-x86_64")
    api(group = "org.bytedeco", name = "leptonica", version = leptonicaVersion, classifier = "macosx-arm64")
    api(group = "org.bytedeco", name = "leptonica", version = leptonicaVersion, classifier = "windows-x86_64")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-javacv")
}
