package com.example.ktor.client


import android.R.attr.mode
import com.example.ktor.model.Post
import com.example.ktor.model.Comment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.compression.ContentEncodingConfig
import io.ktor.client.plugins.compression.compress
import io.ktor.client.request.header


class KtorClient {


    // https://jsonplaceholder.typicode.com/posts
    companion object {

        fun getClient(): HttpClient = HttpClient (CIO) {

            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 3000
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
            }

            install(ContentEncoding) {
                gzip(1f)
                mode = ContentEncodingConfig.Mode.CompressRequest
            }

            install(DefaultRequest) {
                url {
                    //host = "jsonplaceholder.typicode.com"
                    host = "lab.internet-box.ch"
                    protocol = URLProtocol.HTTPS
//                    headers {
//                        append(HttpHeaders.Authorization, "faklfjalkfjlasjfalksf")
//                    }
                }
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

        }


    }

    suspend fun getPosts(): List<Post> {
        return getClient().get("/posts.json")
            .body<List<Post>>()
    }

    suspend fun postPost(post: Post): String? {

        val response: HttpResponse = getClient().post {
            url {
                path("/post.php")
            }
//            contentType(ContentType.Application.Json)
//            setBody(post)
            header("Content-Type", "application/json")
            header("Content-Encoding", "gzip")
            setBody(post)
            compress("gzip")
        }

        return response.body()
    }
//
//    suspend fun patch(map: Map<String, String>, id: Int): Post {
//        return getClient().patch {
//            url {
//                path("/posts/${id}")
//                contentType(ContentType.Application.Json)
//                setBody(map)
//            }
//        }.body<Post>()
//    }
//
//    suspend fun put(post: Post, id: Int): Post {
//        return getClient().put {
//            url {
//                path("/posts/${id}")
//                contentType(ContentType.Application.Json)
//                setBody(post)
//            }
//        }.body<Post>()
//    }
//
//    suspend fun delete(id: Int): HttpResponse {
//        return getClient().delete {
//            url {
//                path("/posts/${id}")
//            }
//        }
//    }
//
//    // https://jsonplaceholder.typicode.com/comments?postId=1
//    suspend fun getComments(id: Int): List<Comment> {
//        return getClient().get {
//            url {
//                path("/comments")
//                parameter("postId", id)
//            }
//        }.body<List<Comment>>()
//    }
//
}