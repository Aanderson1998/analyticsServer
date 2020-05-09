/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//script tag that can be added to any html page to track users

//getting id either from local storage or creating new one and putting in local storage (if none to begin with)
            let id = localStorage.getItem('user');
            if (id === null) {
                console.log("creating new id");
                id = newID();
                localStorage.setItem('user', id);
                console.log("new id is " + id);
                //function to create new id    
                function newID() {
                    // Nice ID format. I might recommend setting id on the outside and just returning
                    // the generated value (you set the outside-scoped id here)
                    id = '_' + Math.random().toString(36).substr(2, 9);
                    return id;
                }
                ;
            } else {
                console.log("id in local storage. It is " + id);
            }

            //getting url
            let url = window.location.href;
            console.log(url);
            //getting browser
            var browser, temp = navigator.userAgent;
                //checking for firefox
            if (temp.indexOf("Firefox") > -1) {
                browser = "Firefox";
                //checking for samsung
            } else if (temp.indexOf("SamsungBrowser") > -1) {
                browser = "Samsung";
                //checking for Opera
                // Small browser compat issue here. sUsrAg doesn't exist. Worked in Firefox
            } else if (temp.indexOf("Opera") > -1 || temp.indexOf("OPR") > -1) {
                browser = "Opera";
               //checking for trident
            } else if (temp.indexOf("Trident") > -1) {
                browser = "Trident";
                // checking for edge
            } else if (temp.indexOf("Edge") > -1) {
                browser = "Edge";
                //checking for chrome
            } else if (temp.indexOf("Chrome") > -1) {
                browser = "Chrome";
                // checking for safari
            } else if (temp.indexOf("Safari") > -1) {
                browser = "Safari";
                // else is unknown
            } else {
                browser = "unknown";
            }
            console.log(browser);

            //getting screen size
            let height = window.screen.height;
            let width = window.screen.width;
            let screenSize = height + "x" + width;
            console.log("Screen size: " + screenSize);


            //creating and executing get request
            // fetch might be a bit easier here.
            if (window.XMLHttpRequest) {
                http = new XMLHttpRequest();
            } else {
                // code for older browsers
                http = new ActiveXObject("Microsoft.XMLHTTP");
            }
            http.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                            let response=this.responseText;
                            console.log(response);
                }
            };
            //adding info to url for get request
            let GetUrl = 'http://localhost:8081/track-view?id=' + id + '&url=' + url + '&browser=' + browser + '&screenSize=' + screenSize;
            http.open("GET", GetUrl, true);
            http.send();
            console.log(GetUrl);


