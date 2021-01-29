<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
</style>

<style>
    body {
        background-color: #fcf9f6;
    }

    .post_wrap {
        width: 1000px;
        height: auto;
        background-color: white;
        margin: 200px auto;
        border: 1px solid #eef0ed;

    }

    .post {
        margin: 80px auto 65px;
    }

    .post_date {
        text-align: right;
        height: 40px;
        font-family: 'Nanum Gothic', sans-serif;
        font-weight: 800;
    }

    .post_title {
        border: 1px solid;
        width: 800px;
        height: 50px;
    }

    .post_photoinput {
        margin: 20px 0;
    }

    .post_content {
        resize: none;
        width: 800px;
        font-size: 1.5em;
        font-family: 'Nanum Gothic', sans-serif;
    }

    .post_cnclorsubmt {
        text-align: right;
    }

    .post_cnclorsubmt>input {
        margin: 20px 0 0 20px;
        width: 100px;
        height: 40px;
        font-size: 1.1em;
        font-family: 'Nanum Gothic', sans-serif;
    }
</style>

<body>

    <div class="post_wrap">
        <table class="post">
            <tr class="post_date">
                2021.01.29
            </tr>
            <tr>
                <input type="text" class="post_title">
            </tr>
            <tr>
                <img src="../imgs/photo.png" class="post_photoinput">
            </tr>
            <tr>
                <textarea rows="20" class="post_content"></textarea>
            </tr>
            <tr class="post_cnclorsubmt">
                <input type="button" value="취소">
                <input type="submit" value="등록">
            </tr>
        </table>
    </div>

</body></html>
