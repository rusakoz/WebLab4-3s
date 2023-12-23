const path = require('path'); //утилита path для построения системных путей (*)
const { VueLoaderPlugin } = require('vue-loader'); // разбирает vue компоненты на части (**)

module.exports = {
  mode: 'development',
  devtool: 'source-map',
  entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'), // (*)
  stats: "errors-only",
  devServer: {
    // до 4-й версии webpack вместо static нужно contentBase: './dist',
    static: {
      directory: path.join(__dirname, './dist'),
    },
    compress: true,
    port: 8081,
    // то, кому разрешен доступ к серверу webpack
    allowedHosts: [
      'localhost:8887' // адрес сервера
    ],
    client: {
      logging: 'error'
    },
  },
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
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin() // (**)
  ],
  resolve: {
    modules: [
      path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
      path.join(__dirname, 'node_modules'),
    ],
  }
}
