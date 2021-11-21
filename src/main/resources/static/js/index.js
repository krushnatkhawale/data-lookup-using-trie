var card = '<div class="p-8">' +
    '    <div class="bg-white p-6 rounded-lg shadow-lg">' +
    '        <div class="flex">' +
    '            <div>' +
    '                <div class="w-10 h-10 bg-cover bg-center rounded-full mr-3 shadow-inner" style="background-image: url(\'\')">' +
    '                </div>' +
    '            </div>' +
    '            <div>' +
    '                <p class="text-gray-600 font-medium">{{username}}</p>' +
    '                <div class="flex items-center text-xs text-gray-600">' +
    '                    <p> {{userid}}</p>' +
    '                    <p class="px-1"> - •</p>' +
    '                    <p>{{tweetDate}}</p>' +
    '                </div>' +
    '            </div>' +
    '        </div>' +
    '        <div class="mt-4">' +
    '            <p class="text-gray-600 text-sm"> {{tweetText}} </p>' +
    '        </div>' +
    '    </div>' +
    '</div>';

var  blog_data;
var post_offset = 0;

var nasaApiUrl = "http://localhost:8080/data";
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      // Typical action to be performed when the document is ready:
      console.log("Started");
      var data = JSON.parse(xhttp.responseText);
      blog_data = data;
      console.log("Data size: ", blog_data.length);
      var tData = blog_data.slice(post_offset, post_offset + 500);
      appendPage(tData, post_offset, post_offset + 500);
      console.log("Completed");
    } else {
      console.log("Http issue while talking to ", nasaApiUrl, ", status is ", this.status)
    }
};
xhttp.open("GET", nasaApiUrl, true);
xhttp.send();

function appendCard(parentDiv, fields){

    var div = document.createElement('div');
    var updatedCard = card.replace("{{username}}", fields[2])
                           .replace("{{userid}}", fields[0])
                           .replace("{{tweetDate}}", fields[1])
                           .replace("{{tweetText}}", fields[3]);
    div.innerHTML = updatedCard;
    parentDiv.appendChild(div);

}

function appendPage(tData, pageStart, pageEnd){

    console.log("New pageStart: ", pageStart, ", pageEnd: ", pageEnd);
    var parentDiv = document.getElementById('gallery-container');

    for(var i=0;i<tData.length;i++){

        var fields = tData[i].split(',');
        appendCard(parentDiv, fields)

    }
}

function getPost() {
  if(post_offset < blog_data.length){
    post_offset = post_offset + 500;
    var pageStart = post_offset, pageEnd = post_offset + 500;
    var tData = blog_data.slice(pageStart, pageEnd);
    appendPage(tData, pageStart, pageEnd);
  }
}

function showLoading() {
  if(post_offset < blog_data.length){
    getPost(); c5
  }
  else{
    // end has been reached, no more posts available
  }
}

window.addEventListener('scroll', () => {
  const { scrollTop, scrollHeight, clientHeight } = document.documentElement;

  if(clientHeight + scrollTop >= scrollHeight - 5) {
    // show the loading animation
    showLoading();
  }
});