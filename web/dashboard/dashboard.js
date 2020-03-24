/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getInfo() {
    var obj = {};
    //getting elements from html page
    obj.users = document.getElementById('users');
    obj.browsers = document.getElementById('browsers');
    obj.urls = document.getElementById('urls');
    obj.requests = document.getElementById('requests');
    obj.chart = document.getElementById('myChart').getContext("2d");
    obj.startDate = document.getElementById('startDate').value;
    obj.endDate = document.getElementById('endDate').value;

    //clearing out elements so the page is empty
    obj.users.innerHTML = "";
    obj.browsers.innerHTML = "";
    obj.urls.innerHTML = "";
    obj.requests.innerHTML = "";

    //getting start and end dates to add to fetch url
    obj.startDate = obj.startDate.split(' ').join('');
    obj.endDate = obj.endDate.split(' ').join('');
    var inputCheck = document.getElementById('inputCheck');
    inputCheck.innerHTML = "";

    //getting url
    let url = 'http://localhost:8081/views?startDate=' + obj.startDate + '&endDate=' + obj.endDate;

    //function to handle errors
    function handleErrors(response) {
        if (!response.ok) {
            inputCheck.innerHTML = "Error dates entered are invalid";
        }
        return response;
    }
    //fetching url and getting data
    fetch(url)
            .then(handleErrors)
            .then(response => response.json())
            .then(viewsInfo => {
                console.log(viewsInfo);
                console.log(viewsInfo.uniqueUsers);
                console.log(viewsInfo.browsers);
                console.log(viewsInfo.URLs);
                console.log(viewsInfo.requests);
                //getting number of unique users
                obj.users.innerHTML = viewsInfo.uniqueUsers;
                //getting browsers list
                obj.browsers.innerHTML = "";
                var browserList = viewsInfo.browsers;
                for (var i = 0; i < browserList.length; i++) {
                    var listEle = document.createElement("li");
                    listEle.innerHTML = browserList[i];
                    obj.browsers.appendChild(listEle);
                }
                //getting urls list
                obj.urls.innerHTML = "";
                var urlList = viewsInfo.URLs;
                for (var i = 0; i < urlList.length; i++) {
                    var listEle = document.createElement("li");
                    listEle.innerHTML = urlList[i];
                    obj.urls.appendChild(listEle);
                }
                //getting requests list
                obj.requests.innerHTML = "";
                var requestList = viewsInfo.requests;
                var x = [];
                var y = [];
                for (var i = 0; i < requestList.length; i++) {
                    var listEle = document.createElement("li");
                    listEle.innerHTML = "Start Time: " + requestList[i].time + " Number of requests: " + requestList[i].requests;
                    obj.requests.appendChild(listEle);
                    x.push(requestList[i].time);
                    y.push(requestList[i].requests);
                }
                //creating chart
                console.log("time " + x);
                console.log("number of requests " + y);
                BuildChart(x, y, "Number of Requests over Time");
                function BuildChart(labels, values, chartTitle) {
                    obj.chart = document.getElementById("myChart").getContext('2d');
                    let chart = new Chart(obj.chart, {
                        type: 'line',
                        data: {
                            labels: labels,
                            datasets: [{
                                    label: chartTitle,
                                    display: 'false',
                                    borderColor: "red",
                                    pointBackgroundColor: "red",
                                    data: values,
                                    fill: false
                                }]
                        },
                        options: {
                            title: {
                                display: true,
                                text: 'Number of Requests Over Time'
                            },
                            scales: {
                                yAxes: [{
                                        ticks: {
                                            beginAtZero: true
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Number of Requests",
                                            fontColor: "black"
                                        }
                                    }],
                                xAxes: [{
                                        scaleLabel: {
                                            display: true,
                                            labelString: "Time",
                                            fontColor: "black"
                                        }
                                    }]
                            }
                        }

                    });
                }
                ;
            });
    return obj;
}
;
