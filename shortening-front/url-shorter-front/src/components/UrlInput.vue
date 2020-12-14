<template>
  <div id="url_input">
    <input type="text" id="inputUrl" placeholder="Enter the URL!!" />
    <button id="send_url_btn" @click="submit">Search</button>
    <p id="result" style="display:none">Hello</p>
    <button id="redirect" style="display:none" @click="redirect">redirect</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      status: 0,
      success: false,
      message: '',
      shortestUrl: '',
      originUrl: ''
    }
  },
  methods: {
    submit: function () {
      var input = document.getElementById('inputUrl').value
      var result = document.getElementById('result')
      var redirect = document.getElementById('redirect')
      if (input === null || input === undefined || input === '') {
        alert('Enter the URL!!!!!!!!!')
        result.innerHTML = ''
        redirect.style.display = 'none'
      } else {
        axios.post('http://localhost:8001/url', {
          url: input
        }).then((res) => {
          console.log(res.headers.status)
          this.status = res.data.status
          this.success = res.data.success
          this.message = res.data.message
          this.shortestUrl = res.data.resultUrl
          if (res.status === 200) {
            if (res.data.success) {
              result.innerHTML = this.shortestUrl
              result.style.display = 'block'
              redirect.style.display = 'block'
            } else {
              result.innerHTML = 'Invaild URL'
              result.style.display = 'block'
              redirect.style.display = 'none'
            }
          }
          console.log(res.data.status)
          console.log(res.data.success)
          console.log(res.data.message)
          console.log(res.data.resultUrl)
        }, function () {
          alert('400 Error : Bad Request')
          result.innerHTML = ''
          redirect.style.display = 'none'
        })
      }
      document.getElementById('inputUrl').value = ''
    },
    redirect: function () {
      var compressUrl = this.shortestUrl.substr(22)
      axios.get('http://localhost:8001/', {
        params: {
          shortUrlAddress: compressUrl
        }
      }).then((res) => {
        console.log(res.headers.status)
        console.log(res.data)
        this.originUrl = res.data.originUrl
        if (res.status === 200) {
          window.location.href = this.originUrl
        }
      }, function () {
        alert('400 Error : Bad Request')
      })
    }
  }
}
</script>

<style scoped>
div {
  margin: 0 15% 0 15%;
  display: block;
  align-items: center;
  justify-content: center;
}
input {
  display: block;
  width: 80%;
  text-align: center;
  margin:auto;
  padding: 10px 15px;
  font-size: 14px;
  line-height: 1.5;
  border: 1px solid #cccccc;
  border-radius: 5px;
}
button {
  background-color: #e08128;
  width: 100px;
  height: 35px;
  border: none;
  border-radius: 3px;
  display: block;
  align-content: center;
  text-align: center;
  margin:auto;
  margin-top: 20px;
}
p{
  text-align: center;
  display: block;
  margin-top: 20px;
  color: red;
}
</style>
