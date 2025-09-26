const modalWindow = document.getElementById('editModal');

function deleteButtonClicked(event) {
    const form = document.getElementById('delete-inside-edit-form');
    console.log(form);
    const ok = confirm("Are you sure you want to delete?");

    if (ok) {
        form.submit();
    }
}


function getData(event) {
    const allTr = document.querySelectorAll('tr');

    for (let tr of allTr) {
        if (!tr.classList.contains('booking-info')) {
            continue;
        }
        for (let td of tr.querySelectorAll('td')) {
            if (td.classList.contains('td-button') && td.querySelector('input') === event.target) {

                return [
                    tr.querySelector('td.title'),
                    tr.querySelector('td.start-time'),
                    tr.querySelector('td.end-time'),
                    tr.querySelector('td.status'),
                ];

            }
            // console.log(td);
        }
        // console.log();
    }
}


function formatDateTime(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    // console.log(`${year}-${month}-${day}T${hours}:${minutes}`);
    return `${year}-${month}-${day}T${hours}:${minutes}`;
}


function editButtonClicked(event) {
    modalWindow.style.visibility = "visible";

    const trow = getData(event);

    const startTime = document.getElementById('modal-start-time');
    const endTime = document.getElementById('modal-end-time');

    startTime.value = formatDateTime(new Date(trow[1].firstChild.textContent));
    endTime.value = formatDateTime(new Date(trow[2].firstChild.textContent));
}


function cancelButtonClicked() {
    modalWindow.style.visibility = "hidden";
}


const initDates = (selector) => {
    document.querySelectorAll(selector)
        .forEach(el => {
            // console.log(el.innerText);
        });
}

initDates('td.start-time');
initDates('td.end-time');