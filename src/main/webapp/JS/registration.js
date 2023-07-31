const password = document.querySelector("#password")
const repassword = document.querySelector("#repassword")
const checkPass = document.querySelector("#checkPass")
const email = document.querySelector("#email")
const text = /[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z]{2,}/;
const checkEmail = document.querySelector("#checkEmail")


repassword.addEventListener("change", function(){
    if(repassword.value!==password.value) {
        checkPass.classList.remove("d-none")
        checkPass.classList.add("d-block")
    } else{
        checkPass.classList.add("d-none")
        checkPass.classList.remove("d-block")
    }
})

email.addEventListener("change", function(){
    if(text.test(email.value)===false) {
        checkEmail.classList.remove("d-none")
        checkEmail.classList.add("d-block")
    } else{
        checkEmail.classList.add("d-none")
        checkEmail.classList.remove("d-block")
    }
})

