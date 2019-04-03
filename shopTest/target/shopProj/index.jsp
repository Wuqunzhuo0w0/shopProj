<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <title>模拟用户登陆</title>
    <link rel="stylesheet" href="css/bootstrap.css">

</head>
<body>
<h1>中文汉字</h1>
<div class="container">
    <div class="container-signin" style="height: 30%; width: 40% ; margin:0 auto ">
        <form class="form-signin" action="/tfkz/user/login" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="email" class="sr-only">Email address</label>
            <input type="email" name="email" id="email" class="form-control" placeholder="Email address" required autofocus>
            <label for="psd" class="sr-only">Password</label>
            <input type="password"  name="psd" id="psd" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>
</div> <!-- /container -->
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script  src="js/jquery-3.3.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.js"></script>
</body>
</html>