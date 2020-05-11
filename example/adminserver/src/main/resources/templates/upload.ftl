<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传下载</title>
</head>
<body>
<p>单文件上传</p>
<form action="upload" method="post" enctype="multipart/form-data">
    文件:<input type="file" name="file"/>
    <input type="submit"/>
</form>
${result}
<hr/>
<p>文件下载</p>
<form action="batch" method="post" enctype="multipart/form-data">
    <p>文件1:<input type="file" name="file"/></p>
    <p>文件2:<input type="file" name="file"/></p>
    <p><input type="submit" value="上传"/></p>
</form>

<!--引入vue和axios-->
<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
<script type="text/javascript">

    // 获取axios post 以multipart/form-data形式上传时的配置参数
    // callBack为上传进度回调函数
    getAxiosMultiPostConfig = function (callBack) {
        // 上传进度回调
        function onUploadProgress(progressEvent) {
            if (progressEvent.lengthComputable) {
                let val = (progressEvent.loaded / progressEvent.total * 100);
                val = parseInt(val)
                // 这里的进度只能表明文件已经上传到后台，但是后台有没有处理完还不知道
                // 因此不能直接显示为100%，不然用户会误以为已经上传完毕，关掉浏览器的话就可能导致上传失败
                // 等响应回来时，再将进度设为100%
                if (val < 100)
                    callBack(val)
            }
        }
        let config = {
            headers: {'Content-Type': 'multipart/form-data'},
            onUploadProgress: onUploadProgress
        };
        return config
    }

    new Vue({
        el : "#app",
        data(){
            return {
                file: null,
                uploadPercent: 0,//上传进度
            }
        },
        mounted(){

        },
        methods:{
            submit(){
                //表单上传
                let formData = new FormData();
                formData.append("file", this.file)
                axios.post("/admin/product/add",formData,getAxiosMultiPostConfig((val)=>{
                    this.uploadPercent = val
            }))
            .then(response => {
                    this.uploadPercent = 100
                alert("上传成功")
            })
            .catch(error => {
                    //hander error
                })
            .then(() => {
                    //always excute
                });
            },
            // 文件选择
            handleFileChange(event,key) {
                let file = event.target.files[0]
                this.file = file
            }
        }
    })
</script>

</body>
</html>
