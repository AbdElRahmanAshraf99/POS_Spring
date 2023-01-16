$(document).ready(() => {
    $.get(`${window.location.origin}/allModules`, (data) => {
        renderNavbar(data);
        $("#box").height($("body").height() - $("nav").height())
        let searchParams = new URLSearchParams(window.location.search)
        if (!searchParams.has('entity')) {
            drawHomePage(data);
        } else {
            let entity = searchParams.get('entity');
            let view = searchParams.get('view');
            if (view === "listView") {
                $.get(`${window.location.origin}/${entity}/all`, (data) => drawListView(data));
            } else {
                $.get(`${window.location.origin}/${entity}/fieldsInfo`, (data) => drawEditView(data));
            }
        }
    })
});

function drawHomePage(data) {
    let content = "<div class='d-flex flex-column' style='width: 100%;'>";
    data["modules"].forEach(module => {
        content += `<div>
            <h2 class="m-0 mx-1 mt-2" style="color: white">${module}</h2>
            <hr class="mx-1 mt-0" style="background-color: white">
            <div class="d-flex" style="width: 100%">`
        data[module].forEach(entity => {
            content += `<a id="${entity.link}" href="/home.html?entity=${entity.link}&view=listView" class="m-3 d-flex justify-content-center align-items-center" style="width: 250px;height: 150px;
            background-color: #ffe715;color: #101920;font-size: 26px;font-weight: 500;cursor: pointer;text-decoration: none">${entity.name}</a>`
        })
        content += `</div></div>`
    })
    content += `</div>`
    $("#box").prepend(content);
}


function drawEditView(data) {
    let content = `<form class="my-2 mx-4" id="needs-validation" style="width: 80%;color: white" novalidate>`
    data.sort((a, b) => a.order < b.order ? -1 : a.order > b.order ? 1 : 0);
    for (let field of data) {
        if (field.embeddable)
            continue
        content += `<div class="mb-3">
            <label class="form-label">${field.text}</label>
            <input id="${field.name}Input" type="${field.inputType}" class="form-control" ${field.required ? "required" : ""}>
            <div class="invalid-feedback">Please provide a valid ${field.text}.</div>
            </div>`
    }
    content += `<button type="submit" class="btn btn-primary" id="submitForm">Submit</button></form>`;
    $("#box").prepend(content);
    $("#submitForm").on('click', (e) => {
        e.preventDefault();
        e.stopPropagation();
        $("#needs-validation").addClass('was-validated');
        let result = {};
        for (let field of data) {
            let fieldName = field.name;
            if (field.embeddable) {
                result[fieldName] = result[fieldName] === undefined ? {} : result[fieldName];
            } else if (field.parent) {
                let parentObject = result[field.parent];
                if (parentObject === undefined)
                    parentObject = {};
                parentObject.fieldName = $(`#${fieldName}Input`).val();
            } else {
                result[fieldName] = $(`#${fieldName}Input`).val();
            }
        }
        console.log(result);
    })
}

function drawListView(data) {
    if (!data["values"].length) {
        $("#box").prepend("<div class='d-flex justify-content-center align-items-center' " +
            "style='font-size: 38px;width: 100%;color: #ffffff'>There is no data to show</div>");
        return;
    }
    let content = `<table class="table table-bordered table-dark table-hover" style="height: fit-content"><thead><tr>`
    for (let field of data["fields"]) {
        content += `<th><div class="d-flex justify-content-around">${field}</div></th>`;
    }
    content += `<th><div class="d-flex justify-content-around">Actions</div></th>`;
    content += `</tr></thead><tbody>`;
    for (let object of data["values"]) {
        content += `<tr>`;
        for (let field of data["fields"]) {
            content += `<td><div class="d-flex justify-content-around">${object[field]}</div></td>`;
        }
        content += `<td><div class="d-flex justify-content-around">
        <button class="btn btn-warning" style="width: 25%" data-id=${object["Id"]}>Edit</button>
        <button class="btn btn-danger" style="width: 25%" data-id=${object["Id"]}>Delete</button>
        </div></td>`;
        content += `</tr>`;
    }
    content += `</tbody></table>`;
    $("#box").prepend(content)
}

function renderNavbar(data) {
    let navbarContent = `
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-0">
            <div class="container-fluid">
            <span class="me-4 mb-0 display-6" style = "color: lime" > POS </span>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="modules">
            <li class="nav-item d-flex align-items-center">
                <a class="nav-link active h5 m-0" aria-current="page" href="/home.html">Home</a>
            </li>`;

    let modules = data["modules"];
    for (let module of modules) {
        navbarContent += `<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active h5 m-0" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            ${module}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">`
        data[module].forEach(entity => {
            navbarContent += `<li><a class="dropdown-item" onclick="" href="/home.html?entity=${entity.link}&view=listView">${entity.name}</a></li>`
        })
        navbarContent += `</ul></li>`
    }
    navbarContent += `</ul>`;
    let searchParams = new URLSearchParams(window.location.search);
    if (searchParams.has('view')) {
        navbarContent += '<button class="btn btn-outline-warning m-2" value="editView" onclick="onChangeViewBtnClicked(this)">' +
            '<i class="bi bi-plus-lg"></i></button>' +
            '<button class="btn btn-outline-warning m-1" value="listView" onclick="onChangeViewBtnClicked(this)">List View</button>';
    }
    navbarContent += `<form class="d-flex">
            <input class="form-control me-2" type = "search" placeholder ="Search" aria-label="Search">
            <button class="btn btn-outline-success" type ="submit">Search</button>
            </form>
    </div>
    </nav>`;
    $("body").prepend(navbarContent);
}

function onChangeViewBtnClicked(target) {
    let searchParams = new URLSearchParams(window.location.search);
    if (!searchParams.has("view")) return;
    let currentView = searchParams.get("view");
    if (target.value === currentView) return;
    searchParams.set("view", target.value);
    location.search = searchParams;
}