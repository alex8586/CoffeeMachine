function count(e) {
    var coinValue = {
        "id":0,
        "value": e
    };
    document.getElementById('emptySum').style.display='none';

    var toSer = JSON.stringify(coinValue);
    var xmlr = new  XMLHttpRequest();
    xmlr.open('POST', 'http://localhost:8080/coffeemachine/addCoin', true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send(toSer);

    xmlr.onreadystatechange = function () {
        if(this.readyState != 4)return;
        if(xmlr.status != 200){

        }
        else{
            returnedCoins.innerHTML = "";
            totalSum.innerHTML = "";
            var resp = JSON.parse(xmlr.response);
            var placeForSumBalance = document.createElement('p');
            placeForSumBalance.id = 'sumBalance';
            if(resp < 1.0)
                placeForSumBalance.innerHTML = resp + " cents";
            else
                placeForSumBalance.innerHTML = resp + " euro";
            totalSum.append(placeForSumBalance);
        }
    };
}

function returnCoins() {

    sumBalance.innerHTML = "";
    var xmlr = new XMLHttpRequest();
    xmlr.open('GET', 'http://localhost:8080/coffeemachine/returnCoins', true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send();

    xmlr.onreadystatechange = function () {
        if (xmlr.readyState != 4)return;
        if (xmlr.status != 200) {

        }
        else {
            productList.innerHTML = "";
            var map = JSON.parse(xmlr.response);

            for(var key in map){
                var coin = key;
                var amount = map[key];
                for(var i = 0; i < amount; i++){
                    var returningCoin = document.createElement('p');
                    if(coin >= 1.0)
                        returningCoin.innerHTML = coin + ' euro';
                    else
                        returningCoin.innerHTML = coin + ' cent';
                    returnedCoins.append(returningCoin);
                }
            }
        }
    };
    setBalanceToZero();
}

function setBalanceToZero() {
    var xmlr = new XMLHttpRequest();
    xmlr.open('DELETE', 'http://localhost:8080/coffeemachine/setToZeroBalance', true);
    xmlr.setRequestHeader('Content-Type', 'application/json');
    xmlr.send();

    xmlr.onreadystatechange = function () {
        if (xmlr.readyState != 4)return;
        if (xmlr.status != 200) {

        }
    };
}

