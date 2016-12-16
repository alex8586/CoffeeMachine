function getListProducts(){
    productList.innerHTML = "";
    var xmlr = new XMLHttpRequest();
    xmlr.open('GET', 'http://localhost:8080/coffeemachine/products', true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send();

    xmlr.onreadystatechange = function () {
        if (xmlr.readyState != 4)return;
        if (xmlr.status != 200) {

        }else{
            var resp = JSON.parse(xmlr.response);
            for(var key in resp){
                var product = resp[key];

                for(var kee in product){
                    if(product.hasOwnProperty(kee)){
                        var pr = product[kee];
                        var prK = kee;

                        if(prK === 'name'){
                            display(pr);

                        }
                        if(prK === 'price'){
                            display(pr);
                        }
                    }
                }
            }
        }
    };
}

function display(msg) {
    var product = document.createElement('span');
    product.setAttribute("class", "enteredData");
    if(typeof msg === "string"){
        createButton(msg);
        product.innerHTML = '&nbsp' +  msg + '&nbsp';
        productList.append(product );
    }else {
        product.innerHTML =  msg + '<br>';
        productList.append(product );
    }
}

function createButton(msg) {
    var button = document.createElement('button');
    var t = document.createTextNode('Get Drink');
    button.setAttribute("class", msg);
    button.id = msg;
    button.onclick = getProduct;
    button.appendChild(t);
    productList.append(button)
}

function getProduct() {
    var productName = this.id;

    var curSum = totalSum.innerHTML;
    if(curSum == ''){
        document.getElementById('emptySum').style.display='block';
        return;
    }
    var currentSum = parseFloat(sumBalance.innerHTML);
    alert(currentSum);

    totalSum.innerHTML = "";

    var xmlr = new XMLHttpRequest();
    xmlr.open('GET', 'http://localhost:8080/coffeemachine/product/' + productName, true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send();

    xmlr.onreadystatechange = function () {
        if (xmlr.readyState != 4)return;
        if (xmlr.status != 200) {

        } else {
            getChange(productName);
            var resp = JSON.parse(xmlr.response);
            var out;
            for(var key in resp){
               if(key === 'name'){
                   out = resp[key];
               }
            }
            
            var productOut = document.createElement('p');
            productOut.id = 'lastProduct';
            productOut.innerHTML = out + '<br>' ;
            var tD = document.getElementById('takeDrink');
            tD.append(productOut);

            var button = document.createElement('button');
            button.id = 'takeDrink';
            var t = document.createTextNode('Take drink!');
            button.appendChild(t);
            button.onclick = buttonTakeDrink;
            tD.append(button);
        }
    }
}

function buttonTakeDrink() {
    productList.innerHTML = "";
    takeDrink.innerHTML = "";
    takeChange.innerHTML = "";
    returnedCoins.innerHTML = "";
    document.getElementById('emptySum').style.display='none';
    setBalanceToZero();
}

function getChange(productName) {
    var product = {
        "id": 0,
        "name": productName,
        "price": 0
    };

    var toServ = JSON.stringify(product);
    var xmlr = new XMLHttpRequest();
    xmlr.open('POST', 'http://localhost:8080/coffeemachine/giveChange', true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send(toServ);

    xmlr.onreadystatechange = function () {
        if (xmlr.readyState != 4)return;
        if (xmlr.status != 200) {

        } else {
            takeChange.innerHTML = "Take your change : <br>";
            var change = JSON.parse(xmlr.response);

            var totalSum = 0;
            for(key in change){
                totalSum += +key * +change[key];
                var coinChange = document.createElement('p');
                coinChange.id = "coinChange";
                coinChange.innerHTML = "coin value " + key + " amount " + change[key];
                takeChange.append(coinChange);
            }
            totalSum = Math.round(totalSum*100)/100;
            var total = document.createElement('p');
            total.innerHTML = "Your change is " + totalSum + " euro";
            takeChange.append(total);
        }
    }
}