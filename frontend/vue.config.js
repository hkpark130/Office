const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  filenameHashing: false, // build 파일 해싱 제거
  productionSourceMap: true, // js source map 생성
  css: {sourceMap: true}    // css source map 생성
})