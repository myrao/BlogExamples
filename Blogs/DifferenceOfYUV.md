#####说明
　　因工作方面接触到图像处理这一块，需要对手机摄像头采集的原始帧做Rotate或者Scale，但无奈对此的了解少之又少，于是网上搜了一顿，完事后将最近所学总结一下，以方便之后的人别踩太多坑。  

　　首先想要了解YUV为何物:[请猛戳我](https://msdn.microsoft.com/en-us/library/aa904813)　　

　　上面的链接中微软已经写的很详细了，国内大部分文章都是翻译这篇文章的，如果还有疑问的同学可以参考下面这些大神的博客：

- [最简单的基于FFmpeg的libswscale的示例（YUV转RGB）](http://blog.csdn.net/leixiaohua1020/article/details/42134965)  
- [图文详解YUV420数据格式](http://www.cnblogs.com/azraelly/archive/2013/01/01/2841269.htm)
- [ANDROID 高性能图形处理](http://tangzm.com/blog/?p=18)
- [Android摄像头开发：实时摄像头视频预览帧的编码问题（二）](http://blog.csdn.net/yanzi1225627/article/details/8626411)

---
　　看完上面的文章应该都会有所了解和认识了，因为在**Android SDK <= 20（Android5.0）**中Google支持的Camera Preview Callback的YUV常用格式有两种：NV21 / YV12，在此针对这两种格式做分析。

#####NV21：

引用一段微软的叙述：

######4:2:0 Formats, 12 Bits per Pixel

>Four 4:2:0 12-bpp formats are recommended, with the following FOURCC codes:  
- IMC2  
- IMC4  
- YV12  
- NV12  
	In all of these formats, the chroma channels are subsampled by a factor of two in both the horizontal and vertical dimensions. 

#####YV12

>`All of the Y samples appear first in memory as an array of unsigned char values.` This array is followed immediately by all of the V (Cr) samples. The stride of the V plane is half the stride of the Y plane, and the V plane contains half as many lines as the Y plane. The V plane is followed immediately by all of the U (Cb) samples, with the same stride and number of lines as the V plane (Figure 12).  
**Figure 12: YV12 memory layout**

#####NV12

>`All of the Y samples are found first in memory as an array of unsigned char values with an even number of lines.` The Y plane is followed immediately by an array of unsigned char values that contains packed U (Cb) and V (Cr) samples, as shown in Figure 13. When the combined U-V array is addressed as an array of little-endian WORD values, the LSBs contain the U values, and the MSBs contain the V values. NV12 is the preferred 4:2:0 pixel format for DirectX VA. It is expected to be an intermediate-term requirement for DirectX VA accelerators supporting 4:2:0 video.  
**Figure 13: NV12 memory layout**

　　从上可知YV12和NV12所占内存是12bits/Pixel，因为每个Y就是一个像素点，注意红色加粗的叙述，YUV值在内存中是按照数组的形式存放的，而由于YV12和NV21都是属于planar格式，也就是Y值和UV值是独立采样的：

　　In a planar format, the Y, U, and V components are stored as three separate planes.

　　既然Y、U、V值都是独立的，那就意味着我们可以分别处理相应的值，比如在YV12中，排列方式是这样的，每4个Y共用一对UV值，而U、V值又是按照如下格式排列（下面是YV12格式中，宽为16，高为4像素的排列） :

Y第一行：|　Y　　Y    |    Y　　Y   |   Y　　Y  |   Y　　Y  |

Y第二行：|　Y　　Y    |    Y　　Y   |   Y　　Y  |   Y　　Y  |

--------------------------------------------------------------

Y第三行：|　Y　　Y    |    Y　　Y   |   Y　　Y  |   Y　　Y  |

Y第四行：|　Y　　Y    |    Y　　Y   |   Y　　Y  |   Y　　Y  |

--------------------------------------------------------------

V第一行：　　V0　　　　V1　　　　V2　　　　　V3　　  |

U第一行：　　U0　　　　U1　　     U2　　　　   U3　　  |

--------------------------------------------------------------

V第二行：　　V4　　　　V5　　　　V6　　　　　V7　　  |

U第二行：　　U4　　　　U5　　  　U6　　　　　U7　　  |

--------------------------------------------------------------

16x4像素的YV12排列

　　

　　知道了YUV值的结构，我们就可以任性的对此图像做Rotate，scale等等。这里我以480*270 （16:9)的一张原始帧图像举例，贴出部分代码示例：

*　　*
