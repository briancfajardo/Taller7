document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    loadPostMsg();
});

function loadPostMsg() {
    let name = document.getElementById("name").value;
    let passw = document.getElementById("password").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.responseText === "true"){
            document.getElementById("secret").setAttribute("style", "display: block;");
        }
        console.log("responseText: ", this.responseText);
    };
    xhttp.open("POST", "/pass");

    xhttp.send(JSON.stringify({
                                user: name,
                                pass: passw
                                }));
}