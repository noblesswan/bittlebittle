const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    port: 4000
  },
  transpileDependencies: true,
  lintOnSave: false
})
