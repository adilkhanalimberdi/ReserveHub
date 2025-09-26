function check() {
    const email = document.getElementById("email");
    const password = document.getElementById("password")
    const form = document.getElementById("login-form");

    const flag1 = checkEmail(email.value.trim());
    const flag2 = checkPassword(password.value.trim());

    // console.log(flag1 + " " + flag2);

    if (!flag1) {
        email.classList.add("error");
    } else {
        email.classList.remove("error");
    }

    if (!flag2) {
        password.classList.add("error");
    } else {
        password.classList.remove("error");
    }

    if (flag1 === true && flag2 === true) {
        form.submit();
    }
}

