
function login(loginForm) {
    axios.post('/admin/login', loginForm)
        .then(function (response) {
            console.info("USER_INFO" + response);
            localStorage.setItem("USER_INFO", response.data)
            //window.load("/admin/index");
        })
}