var webpack = require('webpack');
var path = require('path');
var HtmlWebPackPlugin = require("html-webpack-plugin");
var ExtractTextPlugin = require("extract-text-webpack-plugin");
const CopyWebpackPlugin = require('copy-webpack-plugin')

module.exports = {
    entry: './app/app.js',
    output: {
        path: __dirname + '/dist',
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                // JS LOADER
                // Reference: https://github.com/babel/babel-loader
                // Transpile .js files using babel-loader
                // Compiles ES6 and ES7 into ES5 code
                test: /\.js$/,
                use: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader"
                })
            },
            { test: /\.woff(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&minetype=application/font-woff" },
            { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&minetype=application/octet-stream" },
            { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file" },
            { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&minetype=image/svg+xml" }

            // { test: /\.css$/, loader: "style-loader" },
            // {
            //     test: /\.css$/,
            //     use: [
            //       {
            //         loader: 'css-loader',
            //         options: {
            //           modules: true,
            //           localIdentName: '[path][name]__[local]--[hash:base64:5]'
            //         }
            //       }
            //     ]
            // }
        ]
    },
    devtool: 'source-map',
    devServer: {
        port: 8081,
        contentBase: "./app",
        hot: true,
        historyApiFallback: true,
        proxy: {
            '/api/*': {
                target: 'http://localhost:8080',
                secure: false
            }
        }
    },
    resolve: {
        extensions: ['.js']
    },
    plugins: [
        new HtmlWebPackPlugin({
            template: "./app/index.html"

        }),
        new webpack.HotModuleReplacementPlugin(),
        new ExtractTextPlugin("styles.css"),
        new CopyWebpackPlugin([
            { from: 'app/views', to: 'views'}
        ])
    ]
    // plugins: [
    //     new webpack.ProvidePlugin({
    //         'angular': 'angular'
    //     })
    // ]
};