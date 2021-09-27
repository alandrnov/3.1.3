$(document).ready(function () {
    createTable();
    editUser();
    addUser()
})

// создаем таблицу всех юзеров
async function createTable() {

    let response = await fetch("admin/api")
    let users = await response.json()
    console.log(users)

    for (let i = 0; i < users.length; i++) {

        console.log("отображаем юзера № " + i)
        //
        let id = users[i].id
         let firstName = users[i].firstName
        let secondName = users[i].secondName
         let cellphone = users[i].cellphone
         let roles = [users[i].roles]

        //
        let tr = $("<tr align=\"center\">").attr("id", i)
        tr.append("" +
            "<td>" + id + "</td>" +
             "<td>" + firstName + "</td>" +
             "<td>" + secondName + "</td>" +
             "<td>" + cellphone + "</td>" +
             "<td>" + roles + "</td>" +
            //

            "<td><button onclick='getUserForEdit(" + users[i].id + ")' class='btn btn-md btn-info eBtn' data-toggle='modal' data-target='#editModal'>Edit</button></td>" +
            "<td><button onclick='getUserForDelete(" + users[i].id + ")' class='btn btn-md btn-danger dBtn' data-toggle='modal' data-target='#deleteModal'>Delete</button> </td>"
        )
        $("#allUser").append(tr)
    }
}

// получаем юзера в форму редактирования
async function getUserForEdit(id) {
    console.log("1 получаем юзера в форму редактирования getUserForEdit(id) + " + id)
    let user = await fetch('admin/api/' + id).then(response => response.json())

    $(".editForm #edit_id").val(user.id)
    $(".editForm #edit_login").val(user.login)
    $(".editForm #edit_firstname").val(user.firstName)
    $(".editForm #edit_lastname").val(user.secondName)
    $(".editForm #edit_cell").val(user.cellphone)
    $(".editForm #edit_password").val(user.password)
     //$(".editForm #edit_roles").val(user.roles)
    console.log("2 получаем юзера в форму редактирования getUserForEdit(id)")
}

// отправляем отредактированного юзера
function editUser() {

    console.log("1 отправляем отредактированного юзера")
    $("#editForm").submit(function (event) {
        event.preventDefault()


        let arr = Array.from(document.getElementById("role").options).filter(option => option.selected)
            .map(option => option.value)
            .map(value => {
                return value === 'ROLE_ADMIN'
                    ? {id: 1, role: 'ROLE_ADMIN'}
                    : {id: 2, role: 'ROLE_USER'};
            })

        //
        let editedUser = {
            id: $("#edit_id").val(),
            login: $("#edit_login").val(),
            firstName: $("#edit_firstname").val(),
            secondName: $("#edit_lastname").val(),
            cellphone: $("#edit_cell").val(),
            password: $("#edit_password").val(),
            roles: arr
            //
        }
        console.log(editedUser + "отправляем отредактированного юзера")
        fetch('admin/api/',
            {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(editedUser)
            }).then(function () {
            $('#editModal .close').click();
            $("#allUser tbody").empty();
            createTable();


        })


    })
}
// получаем юзера в форму удаления
async function getUserForDelete(id) {
    console.log("1 получаем юзера в форму удаления getUserForDelete(id) + " + id)
    let user = await fetch('admin/api/' + id).then(response => response.json())

    $(".deleteForm #delete_id").val(user.id)
    $(".deleteForm #delete_login").val(user.login)
    $(".deleteForm #delete_firstname").val(user.firstName)
    $(".deleteForm #delete_lastname").val(user.secondName)
    $(".deleteForm #delete_cell").val(user.cellphone)
    $(".deleteForm #delete_password").val(user.password)
    $(".deleteForm #delete_roles").val(user.roles)

    $("#deleteForm").submit(function (event) {
        event.preventDefault()
        fetch('admin/api/' + id,
            {method: 'DELETE'})
            .then(function () {
                $('#deleteModal .close').click();
                 $("#allUser tbody").empty();
                createTable();
            })
    })
}

// добавляем нового юзера
function addUser() {

    $("#newUser").submit(async function (event) {
        event.preventDefault()

        let arr = Array.from(document.getElementById("role").options).filter(option => option.selected)
            .map(option => option.value)
            .map(value => { return value === 'ROLE_ADMIN'
                ? {id: 1, role: 'ROLE_ADMIN'}
                : {id: 2, role: 'ROLE_USER'};
            })
        //
        let user = {

            login: $("#login").val(),
            firstName: $("#firstname").val(),
            secondName: $("#lastname").val(),
            cellphone: $("#cell").val(),
            password: $("#password").val(),
            roles: arr
            //
        }

        await fetch('admin/api/',
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(user)
            })
            .then(function (){

                $('[href="#users"]').tab('show');
                $("#allUser tbody").empty();
                createTable();
                }
            )
            //.then(result => console.log(result))
      //  window.location.href = "/admin"
    })
}
