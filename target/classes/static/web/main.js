$.ajax({
    url: 'http://localhost:8080/api/flowers',
    type: 'get',
    ataType: 'json',
    contentType: "application/json; charset=utf-8",
    processData:false,
    success: function( data ) {
        createDataTable( data );
    }
})

function createDataTable(data) {
    tbody = document.querySelector('#tbodyId');
    $('#tbodyId').empty();

    data.forEach(ele => {
        var tr = document.createElement('tr');
        tr.innerHTML =
            '<td>' +
            ele.sepalLength +
            '</td>' +
            '<td>' +
            ele.sepalWidth +
            '</td>' +
            '<td>' +
            ele.petalLength +
            '</td>' +
            '<td>' +
            ele.petalWidth +
            '</td>' +
            '<td>' +
            ele.specie +
            '</td>';
        tbody.appendChild(tr);
    });
}

function getType() {
    let flower = {
        sepalLength: $( "#sepal_length" ).val(),
        sepalWidth: $( "#sepal_width" ).val(),
        petalLength: $( "#petal_length" ).val(),
        petalWidth: $( "#petal_width" ).val(),
        specie: null
    }

    $.ajax({
        url: 'http://localhost:8080/api/flowers/check',
        type: 'post',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        processData:false,
        data: JSON.stringify( flower ),
        success: function( data ) {
            var result = confirm( "Gelės tipas: " + data.type + "\nAr norite pridėti prie mokomosios aibės?");
            if(result) {
                addNewFlower(flower, data.type);
            }
        },
        error: function() {
            alert( "Blogai įvesti duomenys." );
        }
    });
}

function addNewFlower(flower, type) {
    flower.specie = type;

    $.ajax({
        url: 'http://localhost:8080/api/flowers',
        type: 'post',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        processData:false,
        data: JSON.stringify( flower ),
        success: function( data ) {
            alert( "Sėkmingai pridėta." );
            location.reload();
        },
        error: function() {
            alert( "Įvyko klaida." );
        }
    });
}