$(".add-reservation").click(function () {
    $.ajax({
        type: 'POST',
        url: '/api/reservations/users/'
            + $(this).attr('data-id-user')
            + '/books/'
            + $(this).attr('data-id-book'),
        success: function () {
            alert("Na twój adres e-mail został wysłany link" +
                " - postępuj zgodnie z instrukcją aby potwierdzić rezerwację.");
            location.reload();
        }
    })
});

$(".cancel-reservation").click(function () {
    $.ajax({
        type: 'DELETE',
        url: '/api/reservations/users/'
            + $(this).attr('data-id-user')
            + '/books/'
            + $(this).attr('data-id-book'),
        success: function (result) {
            alert('Rezerwacja książki została anulowana.');
            location.reload();
        }
    });
});
