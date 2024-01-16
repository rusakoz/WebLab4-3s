const path = require('path'); //утилита path для построения системных путей (*)
const { VueLoaderPlugin } = require('vue-loader'); // разбирает vue компоненты на части (**)

module.exports = {
  entry: path.join(__dirname, 'src', 'main', 'resources', 'js', 'main.js'), // (*)
  stats: "errors-only",
  module: {
    // Прогон всех js файлов через babel-loader
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      // Прогон всех vue файлов через vue-loader
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      // Прогон всех css файлов через vue-style-loader и css-loader
      {
        test: /\.css$/,
        use: [
          'vue-style-loader',
          'css-loader'
        ]
      },
    ]
  },
  plugins: [
    new VueLoaderPlugin() // (**)

  ],
  resolve: {
    modules: [
      path.join(__dirname, 'src', 'main', 'resources', 'js'),
      path.join(__dirname, 'node_modules'),
    ],
  }
}
