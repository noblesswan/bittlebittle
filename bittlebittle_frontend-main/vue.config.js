const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer : {
    port : 4000
  },
  pluginOptions: {
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  }, 
  css: {
    loaderOptions: {
      scss: {
		// 이 옵션에 변수를 모아둔 파일을 연결한다.
		additionalData: `
			@import "@/assets/css/_variabled.scss";
                `,
      },
    },
  }
})
