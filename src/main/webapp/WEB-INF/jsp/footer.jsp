<style>
body {
   margin:0;
}
#container {
   margin: 0 auto;
   width: 100%;
}
nav ul {
    list-style: none;
    margin-bottom: 1em;
}

nav ul li {
    height: 2em;
    font-weight: bold;
    padding: 10px;
    width: 100%;
}

nav ul li a{
    color: #000;
    text-decoration: none;
    display: block;
}

h2, main, footer {
    padding: 15px 10px;
}

h1 {
    background-image: url("../images/banner2.jpg");
    height: 300px;
    width: 100%;
    background-repeat: no-repeat;
    background-size: cover;
}

th,td {
    padding: 0.6em 1.2em 0 0;
    text-align: left;
    vertical-align: top;
}

table caption {
    display: none;
}

form {
    margin: 30px 0;
}

.alert-danger {
    padding: 0.5em;
    background-color: #f2dede;
    border: 1px solid #b94a48;
    color: #b94a48;
    border-radius: 3px;
    width: 100%;
    margin-bottom: 1em;
    box-sizing: border-box;
}
.alert-danger ul {
    list-style-type: none;
}
label,input,textarea {
    margin-bottom: 16px;
}
input,textarea {
    border: 1px solid #888;
    margin: 2px 0 16px 0;
    outline: 0 none;
    padding: 0.5em;
    line-height:15px;
    border-radius: 3px;
    box-shadow: inset 0px 1px 4px #ECECEC;
    box-sizing: border-box;
}
.has-error {
    color: #b94a48;
    border-color: #b94a48;
    box-shadow: inset 0px 1px 4px #d59392;
}

/* mobile first */
div#container {
    width: 100%;
}

nav ul li {
    display: block;
    border-bottom: 1px solid #303d11;
}

nav ul li:first-child {
    border-top: 1px solid #303d11;
}

label,input,textarea {
    display: block;
}
input[type="text"], input[type="email"], textarea {
    width: 100%;
}
input {
    height: 25px;
}


/* desktop */
@media screen and (min-width: 650px) {
    div#container {
        max-width: 1000px;
    }
    h1 {
        background-image: url("../images/banner3.jpg");
        background-repeat: no-repeat;
        background-size: cover;
        height: 300px;
    }

    nav ul {
        display: -webkit-box;  /* OLD - iOS 6-, Safari 3.1-6, BB7 */
        display: -ms-flexbox;  /* TWEENER - IE 10 */
        display: -webkit-flex; /* NEW - Safari 6.1+. iOS 7.1+, BB10 */
        display: flex;
        -webkit-flex-direction: row;
        -ms-flex-direction: row;
        flex-direction: row;
        box-sizing: border-box;
    }

    nav ul li {
        width: 33%;
        margin: auto;
        border: none;
    }
    nav ul li:first-child {
        border: none;
    }
    nav ul li a {
        text-align: center;
    }
    footer {
        text-align: right;
    }
    .alert-danger {
        width: 614px;
    }
    label,input,textarea {
        display: inline-block;
    }
    label {
        width: 100px;
        text-align: right;
        margin-right: 10px;
    }
    input[type="text"], input[type="email"], input[type="password"], textarea{
        width: 400px;
    }
    input[type="submit"] {
        margin-left: 112px;
    }
    textarea {
       vertical-align: middle
    }
}
</style>