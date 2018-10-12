$(document).ready(function() {
    $('input').change(function() {
        if ($('input[value="100"]').is(':checked')) {
            $('.JobIdentity').show();
            $('.submitBtn').show();
            $('.JobCatSelection').hide();
        }
        else if ($('input[value="150"]').is(':checked')) {
            $('.JobCatSelection').show();
            $('.submitBtn').show();
            $('.JobIdentity').hide();
        }
    });
});


// var table = document.getElementById('table'), rIndex;
//
// for (var i = 0; i < table.rows.length; i++){
//     table.rows[i].onclick = function () {
//         rIndex = this.rowIndex;
//         document.getElementById("fname").value = this.cells[0].innerHTML;
//         document.getElementById("emailID").value = this.cells[1].innerHTML;
//         document.getElementById("conNos").value = this.cells[2].innerHTML;
//         document.getElementById("qualification").value = this.cells[3].innerHTML;
//
//     };
// }