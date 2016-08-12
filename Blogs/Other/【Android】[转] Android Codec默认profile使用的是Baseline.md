关于Android默认Codec使用的Profile找了半天没发现，还是Google的时候发现了开源中国有网友写的这边博客，相关的内容很少，便贴了过来做个笔记。

#### 以下内容转自[Android MediaCodec 设置 MediaFormat.KEY_PROFILE 问题](http://my.oschina.net/jerikc/blog/483641)

我在设置 MediaCodec profile 的时候，一直没有成功，看了源码之后才发现问题之所在：

https://android.googlesource.com/platform/frameworks/av/+/437ced8a14944bf5450df50c5e7e7a6dfe20ea40/media/libstagefright/ACodec.cpp

![img](http://static.oschina.net/uploads/space/2015/0725/111639_oJW9_172402.png)

设置了 profile 之后，你还要设置一个 Level 属性，但是目前最新的 SDK 里面并没有提供这个 Key。

即使你手动的设置 level ，比如像这样：

```
MediaCodec codec = createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
MediaFormat format = = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, 1920, 1080);
format.setInteger(MediaFormat.KEY_PROFILE, MediaCodecInfo.CodecProfileLevel.AVCProfileHigh);
format.setInteger("level", Level_xxx);

codec.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
```

还是不行的，因为 Android 强制将 profile 设置为 Baseline 了。

```
// XXX
    if (h264type.eProfile != OMX_VIDEO_AVCProfileBaseline) {
        ALOGW("Use baseline profile instead of %d for AVC recording",
            h264type.eProfile);
        h264type.eProfile = OMX_VIDEO_AVCProfileBaseline;
    }
```

总之，Android 在使用 MediaCodec 进行 encode 的时候，只能使用 Baseline 的 profile。

Google 之后，发现也有其他的朋友发现了类似的问题，

https://code.google.com/p/android/issues/detail?id=163580

看来这个问题确实存在，不过不清楚为什么 Android 要强制使用 Baseline 的 profile。