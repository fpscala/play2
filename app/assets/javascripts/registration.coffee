$ ->
  window.Glob ?= {}

  apiUrl =
    reg: '/register/'

  defaultData =
    email: ''
    psw: ''
    comment: ''

  vm = ko.mapping.fromJS
    enteredData: defaultData

  vm.onSubmit = ->
    console.log('data', vm.enteredData)
#    data = vm.enteredData
#    $.ajax
#      url: apiUrl.reg
#      type: 'POST'
#      data: JSON.stringify(data)
#      dataType: 'json'
#      contentType: 'application/json'
#      .fail () ->
#      alert('Error occurred')
#      .done (response) ->
#        alert('Successfully sent')

  ko.applyBindings {vm}
