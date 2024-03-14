document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    loadPostMsg();
});

function loadPostMsg() {
    let name = document.getElementById("password").value;
    let passw = document.getElementById("password").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        //document.getElementById("postrespmsg").innerHTML = this.responseText;
        console.log("responseText: ", this.responseText);
    };
    xhttp.open("POST", "/pass");

    xhttp.send(JSON.stringify({
                                user: name,
                                pass: passw
                                }));
}