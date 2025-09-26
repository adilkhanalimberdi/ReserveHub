const profile_button = document.getElementById("profile");
const profile_info = document.getElementById("profile-info");
const close_zone = document.getElementById("close-zone");

profile_button.onclick = () => {
    if (profile_info.classList.contains("active")) {
        return;
    }
    profile_info.classList.add("active");
    profile_info.classList.remove("none");

    close_zone.classList.add("active");
    close_zone.classList.remove("none");
}

close_zone.onclick = () => {
    profile_info.classList.remove("active");
    profile_info.classList.add("none");

    close_zone.classList.remove("active");
    close_zone.classList.add("none");
}