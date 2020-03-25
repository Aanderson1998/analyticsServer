# analyticsServer

## How To Use
When downloading files, the AnalyticServer folder has all material used to create restful spring server, which I built in eclipse.
All other files and folders belong to the web pages I created, which I created in NetBeans
##
In the web folder you can find two other folders, tag and dashboard.
The tag folder has two demo html pages with the script tag, and the tag.js has the code for the script tag.
The dashboard folder has the html, JavaScript, and css files I used to create the dashboard
##
When running the dashboard.html page and entering dates for the dashboard, enter dates in the format dd-mm-yyyy.
These dates are transformed to timestamps starting at the date plus 00:00:00 (so the very beginning of the day).
So to track data you can only track by days.
For example, to see request made on March 24,2020 the start date would be 24-03-2020 and the end date would have to be 25-03-2020 (if you inputted 24-03-2020 and 24-03-2020 you would get an error)
