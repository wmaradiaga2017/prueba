var tbg; //variable contenedora del datatable
let idemplaedo = 0; //identificador de empleado

var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
});

$(document).ready(function () {

    $("#selectpais").select2({
        placeholder: "Seleccione el país",
        allowClear: true
    });

    tbg = $('#tbpersonal').DataTable({
        dom: '<"row"<"col-md-12"<"row"<"col-md-6"B><"col-md-6"f> > ><"col-md-12"rt> <"col-md-12"<"row"<"col-md-5"i><"col-md-7"p>>> >',
        "stripeClasses": [],
        buttons: [
            'excel', 'pdf', 'print'
        ],
        "oLanguage": {
            "oPaginate": {"sPrevious": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>', "sNext": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>'},
            "sInfo": "Pagina _PAGE_ de _PAGES_",
            "sSearch": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
            "sSearchPlaceholder": "Buscar...",
            "sLengthMenu": "Resultados :  _MENU_",
        },
        "ordering": false,
        "stripeClasses": [],
        "lengthMenu": [7, 10, 20, 50],
        "pageLength": 10
    });

    $("#tel").inputmask("9999-9999");

    cargapersonal();
});


const asignadatatable = () => {
    //tabla de personal
    tbg = $('#tbpersonal').DataTable({
        dom: '<"row"<"col-md-12"<"row"<"col-md-6"B><"col-md-6"f> > ><"col-md-12"rt> <"col-md-12"<"row"<"col-md-5"i><"col-md-7"p>>> >',
        "stripeClasses": [],
        buttons: [
            'excel', 'pdf', 'print'
        ],
        "oLanguage": {
            "oPaginate": {"sPrevious": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>', "sNext": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>'},
            "sInfo": "Pagina _PAGE_ de _PAGES_",
            "sSearch": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
            "sSearchPlaceholder": "Buscar...",
            "sLengthMenu": "Resultados :  _MENU_",
        },
        "ordering": false,
        "stripeClasses": [],
        "lengthMenu": [7, 10, 20, 50],
        "pageLength": 10,
        "columns": [
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            {"width": "20%"},
        ]
    });
    
    
};


const cargapersonal = () => {
    //destruccion datatable
    tbg.destroy();
    $('#tbpersonal').empty();
    //carga
    $.Toast.showToast({"title": "Actualizando espere por favor...", "duration": "0", "icon": "loading"});
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let myArr = JSON.parse(this.responseText);
            if (myArr.response === 200) {
                //carga personal
                let navegacion = myArr.personal;
                let tb = "<thead><tr><th>#</th><th>Nombre</th><th>Apellidos</th><th>Teléfono</th><th>Sitio de trabajo</th><th>País de residencia</th><th>Ciudad de Residencia</th><th>Fecha de nacimiento</th><th>Edad</th><th></th></tr></thead><tbody>";
                for (let i = 0; i < navegacion.length; i++) {
                    let item = navegacion[i];
                    tb += "<tr><td>" + (i + 1) + "</td>";
                    tb += "<td>" + item.nombre + "</td>";
                    tb += "<td>" + item.apellidos + "</td>";
                    tb += "<td>" + item.telefono + "</td>";
                    tb += "<td>" + item.sitio + "</td>";
                    tb += "<td>" + item.pais + "</td>";
                    tb += "<td>" + item.ciudad + "</td>";
                    tb += "<td>" + item.fechanac + "</td>";
                    tb += "<td>" + item.edad + "</td>";
                    tb += "<td><a data-bs-toggle='tooltip' title='Editar empleado' style='cursor:pointer;color:blue;padding:5px;' onclick=\"editarinfo('" + item.id + "')\"><i class='fas fa-edit fa-2x'></i></a><a data-bs-toggle='tooltip' title='Eliminar empleado' style='cursor:pointer;color:red;padding:5px;' onclick=\"deleteinfo('" + item.id + "')\"><i class='fas fa-user-times fa-2x'></i></a></td></tr>";
                    if (i === navegacion.length - 1) {
                        tb += "</tbody>";
                        $("#tbpersonal").append(tb);
                        $.Toast.hideToast();
                        asignadatatable();
                    }
                }
                $.Toast.hideToast();
            } else {
                $.Toast.hideToast();
                notify('top', 'right', 'fa fa-comments', 'inverse', 'animated fadeIn', 'animated fadeOut', 'Ocurrio un problema, por favor intente mas tarde.');
            }
        }
    }
    xhttp.open("POST", "/api/", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();
};



const nuevoempleado = () => {
    $("#content").hide(500);
    $("#btnnuevo").hide(1000);
    $("#formcontent").show(1000);
    $("#btnguardar").show();
    $("#btnedit").hide();
};

const resetempleado = () => {
    $("#formcontent").hide(500);
    $("#content").show(1000);
    $("#btnnuevo").show(1000);
    $("#nombre").val("");
    $("#apellidos").val("");
    $("#tel").val("");
    $("#sitio").val("");
    $("#selectpais").val("-1");
    $("#ciudad").val("");
    $("#fechanac").val("");
};



//guarda nuevo empleado
const guardarempleado = () => {
    //valida campos    
    let nombre = $("#nombre").val();
    let apellidos = $("#apellidos").val();
    let telefono = $("#tel").val();
    let sitiotrabajo = $("#sitio").val();
    let pais = $("#selectpais").val();
    let ciudad = $("#ciudad").val();
    let fecha = $("#fechanac").val();
    if (nombre === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese el nombre.');
        return -1;
    }
    if (apellidos === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese los apellidos.');
        return -1;
    }
    if (pais === "" || pais === "-1") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese el pais.');
        return -1;
    }
    if (fecha === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese la fecha de nacimiento.');
        return -1;
    }
    //envia datos
    $.Toast.showToast({"title": "Guardando empleado espere por favor...", "duration": "0", "icon": "loading"});
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let myArr = JSON.parse(this.responseText);
            if (myArr.response === 200) {
                $.Toast.hideToast();
                resetempleado();
                cargapersonal();
            } else {
                $.Toast.hideToast();
                notify('top', 'right', 'fa fa-comments', 'inverse', 'animated fadeIn', 'animated fadeOut', 'Ocurrio un problema, por favor intente mas tarde.');
            }
        }
    }
    xhttp.open("POST", "/api/guardarempleado", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("nombre=" + nombre + "&apellidos=" + apellidos + "&telefono=" + telefono + "&sitiotrabajo=" + sitiotrabajo + "&pais=" + pais + "&ciudad=" + ciudad + "&fecha=" + fecha);
};


const editarinfofuncional = (id) => {
    idemplaedo = id;
    $("#content").hide(500);
    $("#btnnuevo").hide(1000);
    $("#formcontent").show(1000);
    $("#btnguardar").hide();
    $("#btnedit").show();
};


const deleteinfo = (id) => {
    swal({
        title: "¿Desea eliminar el personal COD:" + id + "?",
        text: "",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-danger",
        confirmButtonText: "Si",
        cancelButtonText: "No"
    },
            function (isConfirm) {
                if (isConfirm) {
                    $.Toast.showToast({"title": "Eliminando espere por favor...", "duration": "0", "icon": "loading"});
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            let myArr = JSON.parse(this.responseText);
                            if (myArr.response === 200) {
                                //Actualiza
                                cargapersonal();
                                $.Toast.hideToast();
                            } else {
                                $.Toast.hideToast();
                                notify('top', 'right', 'fa fa-comments', 'inverse', 'animated fadeIn', 'animated fadeOut', 'Ocurrio un problema, por favor intente mas tarde.');
                            }
                        }
                    }
                    xhttp.open("POST", "/api/eliminarempleado", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("id=" + id);
                }
            });

};



const editarinfo = (id) => {
    //carga empleado
    $.Toast.showToast({"title": "Actualizando espere por favor...", "duration": "0", "icon": "loading"});
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let myArr = JSON.parse(this.responseText);
            if (myArr.response === 200) {
                //carga persona
                let navegacion = myArr.personal;
                if (navegacion.length > 0) {
                    let item = navegacion[0];
                    $("#nombre").val(item.nombre);
                    $("#apellidos").val(item.apellidos);
                    $("#tel").val(item.telefono);
                    $("#sitio").val(item.sitio);
                    $("#selectpais").val(item.pais).trigger('change');
                    $("#ciudad").val(item.ciudad);
                    const datafecha = (item.fechanac).split("/");
                    $("#fechanac").val(datafecha[2] + "-" + datafecha[1] + "-" + datafecha[0]);
                    editarinfofuncional(id);
                }
                $.Toast.hideToast();
            } else {
                $.Toast.hideToast();
                notify('top', 'right', 'fa fa-comments', 'inverse', 'animated fadeIn', 'animated fadeOut', 'Ocurrio un problema, por favor intente mas tarde.');
            }
        }
    }
    xhttp.open("POST", "/api/consultaempleado", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id=" + id);
};



//guarda nuevo empleado
const updateempleado = () => {
    //valida campos    
    let nombre = $("#nombre").val();
    let apellidos = $("#apellidos").val();
    let telefono = $("#tel").val();
    let sitiotrabajo = $("#sitio").val();
    let pais = $("#selectpais").val();
    let ciudad = $("#ciudad").val();
    let fecha = $("#fechanac").val();
    if (nombre === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese el nombre.');
        return -1;
    }
    if (apellidos === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese los apellidos.');
        return -1;
    }
    if (pais === "" || pais === "-1") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese el pais.');
        return -1;
    }
    if (fecha === "") {
        notify('top', 'right', 'fa fa-info-circle', 'warning', 'animated fadeIn', 'animated fadeOut', 'Por favor ingrese la fecha de nacimiento.');
        return -1;
    }
    //envia datos
    $.Toast.showToast({"title": "Actualizando empleado espere por favor...", "duration": "0", "icon": "loading"});
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let myArr = JSON.parse(this.responseText);
            if (myArr.response === 200) {
                $.Toast.hideToast();
                resetempleado();
                cargapersonal();
            } else {
                $.Toast.hideToast();
                notify('top', 'right', 'fa fa-comments', 'inverse', 'animated fadeIn', 'animated fadeOut', 'Ocurrio un problema, por favor intente mas tarde.');
            }
        }
    }
    xhttp.open("POST", "/api/actualizaempleado", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id=" + idemplaedo + "&nombre=" + nombre + "&apellidos=" + apellidos + "&telefono=" + telefono + "&sitiotrabajo=" + sitiotrabajo + "&pais=" + pais + "&ciudad=" + ciudad + "&fecha=" + fecha);
};


