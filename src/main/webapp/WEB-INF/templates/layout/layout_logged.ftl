<#macro page>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fake Book</title>

    <link href="/webapps_security/css/site.css" rel="stylesheet" type="text/css">
    </style>
</head>
<body>

<div id="container">

    <div id="header">
        <div id="logo">
            <h1><a href="/webapps_security/">Fake Book</a></h1>
        </div>

        <div id="logged_info">
            <span id="logged_name">${user.fullName}</span>
            <a href="/webapps_security/messages">Messages</a> | <a href="/webapps_security/logout">Logout</a>
        </div>
    </div>



    <div id="content">

    <#nested/>

    </div>
</div>

</body>
</html>

</#macro>