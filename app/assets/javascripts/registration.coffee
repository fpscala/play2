$ ->
  window.Glob ?= {}
  apiUrl =
    reg: '/register/'

  handleError = (error) ->
      alert('something went wrong')

  vm = ko.mapping.fromJS
    email:'eee'
    psw: ''
    comment: ''

  vm.onSubmit = ->
#    console.log('email', vm.email())
#    console.log('psw', vm.psw())
#    console.log('comment', vm.comment())
    $.ajax
      url: apiUrl.reg
      type: 'POST'
      data: JSON.stringify(
        email: vm.email()
        psw: vm.psw()
        comment: vm.comment()
      )
      dataType: 'json'
      contentType: 'application/json'
    .fail handleError
    .done (response) ->
        alert(response)

  ko.applyBindings {vm}
