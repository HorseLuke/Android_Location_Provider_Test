Android_Location_Provider_Test
===================

运行于安卓上，检测是否能正常初始化“网络定位”功能的小工具。

简介
==

由于有厂商博弈与大环境因素的复杂情况，国行安卓手机/平板的“网络定位”有许多是不能正常工作的，这带来的后果是包括HTML5网络定位不能正常工作等。

虽然有各种教程帖均提及恢复“NetworkLocation.apk”等方式恢复所谓的GMS服务（Google Mobile Services），但如果相关文件没有使用对应的platform证书签名，或者和rom中其它platform签名级别的apk签名不相同，其实均不能使用。这个验证过程很费周折。

本应用则提供一种辅助检查的思路，当前只有检查Android 4.0.x及以下版本的网络定位是否初始化正常。不能正常初始化的话，则“网络定位”一定无法工作。

请注意：初始化检测通过后，如果是使用GMS提供的网络定位功能（也就是传说中的NetworkLocation.apk），而GMS的其它组件存在损坏或者版本不一致，也一样不能使用。

致谢和代码
==

代码参考众多人的代码，在此致谢。参考来源请见注释。

Licensed under the Apache License, Version 2.0 (the "License")
