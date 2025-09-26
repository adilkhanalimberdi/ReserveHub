function checkPassword(password, confirm) {
    if (password !== confirm) {
        return false;
    }
    return (password.length > 0);
}

function check() {
    const name = document.getElementById("name");
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    const confirm = document.getElementById("confirm");
    const form = document.getElementById("registration-form");

    const flag1 = checkName(email.value.trim());
    const flag2 = checkEmail(email.value.trim());
    const flag3 = checkPassword(password, confirm);

    if (!flag1) {
        name.classList.add("error");
    } else {
        name.classList.remove("error");
    }

    if (!flag2) {
        email.classList.add("error");
    } else {
        email.classList.remove("error");
    }

    if (!flag3) {
        password.classList.add("error");
        confirm.classList.add("error");
    } else {
        password.classList.remove("error");
        confirm.classList.remove("error");
    }

    if (flag1 === true && flag2 === true && flag3 === true) {
        form.submit();
    }

}