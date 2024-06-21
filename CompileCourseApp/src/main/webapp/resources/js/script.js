function deleteOutline(url, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(url, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!");

        });
    }
}