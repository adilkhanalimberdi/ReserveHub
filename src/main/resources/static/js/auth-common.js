function checkEmail(email) {

    if (email.split(' ').length > 1) return false;

    const parts = email.split('@');

    if (parts.length !== 2 || parts[0].trim() === "") return false;

    const parts2 = parts[1].split(".");

    return !(parts2.length !== 2 || parts2[1].trim() === "");
}

function checkPassword(password) {
    return (password.length > 0);
}

function checkName(name) {
    return (name.length > 0);
}

document.addEventListener("keydown", (event) => {
    const button = document.querySelector(".submit");
    if (event.key === "Enter") {
       button.click();
   }
});