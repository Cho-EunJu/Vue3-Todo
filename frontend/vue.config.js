const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy:{
      "/api":{
        "target" : "http://192.168.1.13:8090",
        "pathRewrite" : {'^/' : ''},
        "changeOrigin": true,
        "ws": false
      }
    },

  }
})
