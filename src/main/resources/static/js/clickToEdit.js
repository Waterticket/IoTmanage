"use strict";
function clickToEdit(index) {
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/edit");
    let hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden');
    hiddenField.setAttribute('name', 'id');
    hiddenField.setAttribute('value', String(index));
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}
