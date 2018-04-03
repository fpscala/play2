$ ->
  window.Glob ?= {}
  apiUrl =
    reg: '/register/'
    users: '/get-users/'

  handleError = (error) ->
      alert('something went wrong')

  vm = ko.mapping.fromJS
    email:'eee@qwe'
    psw: ''
    comment: ''
    slanguages: []
    planguage: ''
    users: []

  vm.onSubmit = ->
    console.log('email', vm.email())
    console.log('slanguages', vm.slanguages())
    console.log('planguage', vm.planguage())
    console.log('psw', vm.psw())
    console.log('comment', vm.comment())
    $.ajax
      url: apiUrl.reg
      type: 'POST'
      data: JSON.stringify(
        email: vm.email()
        psw: vm.psw()
        comment: vm.comment()
        sLanguages: vm.slanguages()
        pLanguage: vm.planguage()
      )
      dataType: 'json'
      contentType: 'application/json'
    .fail handleError
    .done (response) ->
        alert(response)
        vm.report()

  vm.report = ->
    $.ajax
      url: apiUrl.users
      type: 'GET'
    .fail handleError
    .done (response) ->
      users = response.users
      console.log(users)
      vm.users(users)
      console.log(vm.users())
      console.log(vm.users().length)

  ko.applyBindings {vm}
