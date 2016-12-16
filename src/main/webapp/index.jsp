<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="css/style.css" /> " >
</head>
<body>
    <div class="wrapper">
        <div class="image">
            <img src="img/coffee_machine.jpg">
            <div class="takeDrink" id="takeDrink" style="text-align: center"></div>
        </div>
        <div class="workSpace">
            <strong>Insert coins:</strong><br><br>

            <button class="coin" value="0.01" onclick="count(this.value)">1 cent</button>
            <button class="coin" value="0.02" onclick="count(this.value)">2 cents</button>
            <button class="coin" value="0.05" onclick="count(this.value)">5 cents</button>
            <button class="coin" value="0.1" onclick="count(this.value)">10 cents</button><br>
            <button class="coin" value="0.2" onclick="count(this.value)">20 cents</button>
            <button class="coin" value="0.5" onclick="count(this.value)">50 cents</button>
            <button class="coin" value="1" onclick="count(this.value)">1 euro</button>
            <button class="coin" value="2" onclick="count(this.value)">2 euro</button><br><br>

            Total sum: <div class="totalSum" id="totalSum"></div>
            <div class="emptySum" id="emptySum">There is no coins in coffeeMachine</div>
            <hr>

            <button class="change" onclick="returnCoins()">Return Coins</button>
            <div class="returnedCoins" id="returnedCoins"></div>
            <hr>

            <button class="listProducts" onclick="getListProducts()">List of Products</button>
            <div class="productList" id="productList"></div>
            <hr>

            <div class="takeChange" id="takeChange"></div>

        </div>

    </div>

<script src="<c:url value="js/coins.js"/>"></script>
<script src="<c:url value="js/products.js"/> "></script>
</body>
</html>
