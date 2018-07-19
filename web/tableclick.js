$(document).ready(function () {
    $('table tbody tr').click(function () {
        alert($(this).text());
    });
});