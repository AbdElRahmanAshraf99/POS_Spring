$(document).ready(() => {
    renderNavbar();
    let searchParams = new URLSearchParams(window.location.search)
    if (!searchParams.has('entity')) {
        console.log("Home")
    } else {
        let entity = searchParams.get('entity');
        let view = searchParams.get('view');
        $("#box").height($(document).height() - $("nav").height())
        if (view === "listView") {
            $.get(`${window.location.origin}/${entity}/all`, (data) => drawListView(data));
        }
    }
});

function drawListView(data) {
    if (!data.length) {
        $("#box").prepend("<div class='d-flex justify-content-center align-items-center' " +
            "style='font-size: 38px;width: 100%;color: #ffffff'>There is no data to show</div>");
        return;
    }
    let content = `<table class="table table-bordered table-dark table-hover" style="height: fit-content"><thead><tr>`
    for (let key in data[0]) {
        if (key === "Id") continue;
        content += `<th><div class="d-flex justify-content-around">${key}</div></th>`;
    }
    content += `<th><div class="d-flex justify-content-around">Actions</div></th>`;
    content += `</tr></thead><tbody>`;
    for (let object of data) {
        content += `<tr>`;
        for (let key in object) {
            if (key === "Id") continue;
            content += `<td><div class="d-flex justify-content-around">${object[key]}</div></td>`;
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

function renderNavbar() {
    let modules = {
        Basic: [{
            text: "Customer",
            link: "customer"
        }, {
            text: "Supplier",
            link: "supplier"
        }, {
            text: "Item",
            link: "item"
        }, {
            text: "User",
            link: "user"
        },],
        Invoices: [{
            text: "Purchase Invoice",
            link: "purchaseInvoice"
        }, {
            text: "Sales Invoice",
            link: "salesInvoice"
        },]
    }
    let navbarContent = `
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-0">
            <div class="container-fluid">
            <span class="me-4 mb-0 display-6" style = "color: lime" > POS </span>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" id="modules">
            <li class="nav-item d-flex align-items-center">
                <a class="nav-link active h5 m-0" aria-current="page" href="/home.html">Home</a>
            </li>`;
    for (let module in modules) {
        navbarContent += `<li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active h5 m-0" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            ${module}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">`
        modules[module].sort((a, b) => a.text - b.text);
        modules[module].forEach(entity => {
            navbarContent += `<li><a class="dropdown-item" onclick="" href="/home.html?entity=${entity.link}&view=listView">${entity.text}</a></li>`
        })
        navbarContent += `</ul></li>`
    }
    navbarContent += `</ul>`;
    let searchParams = new URLSearchParams(window.location.search);
    if (searchParams.has('view')) {
        navbarContent += '<button class="btn btn-outline-warning m-1" value="listView" onclick="onChangeViewBtnClicked(this)">List View</button>' +
            '<button class="btn btn-outline-warning m-2" value="editView" onclick="onChangeViewBtnClicked(this)">Edit View</button>';
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