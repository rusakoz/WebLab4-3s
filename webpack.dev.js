const path = require('path');
const { merge } = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    devtool: 'source-map',
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
});