const express = require('express')
const fs = require('fs')
const app = express()
const port = 3000
app.use(express.json())

app.get('/tasks', (req, res) => fs.readFile('./Todo.json', 'utf-8', (err, data) => {
    if(err){
        res.status(500).send()
    } else {
        res.json(JSON.parse(data))
    }
}))


const asyncReadFile = function(path){
    return new Promise(
        function(resolve, reject){
            fs.readFile(path, 'utf-8', function(err, data){
                if(err){
                    reject(err)
                }
                resolve(data)
            })
         }).catch(err => {
             return err
    })
}

const asyncWriteFile = function(string, path){
    return new Promise(function (resolve, reject){
        fs.writeFile(path, string, function(err){
            reject(err) 
        })
    }).catch(err => {
        return err
    })
}

const createTask = async (req, res) => {
    const newTask = req.body
    const file = await asyncReadFile('./Todo.json') 
    const tasks = JSON.parse(file)
    if(tasks.filter(v => v.id == newTask.id).length != 0){
        res.status(400).send()
    } else {
        tasks.push(newTask)
        await asyncWriteFile(JSON.stringify(tasks), './Todo.json')
        res.status(201).send(tasks)
    }
}

app.post('/tasks',createTask)

app.listen(port, () => console.log(`Our server has been setup,  and listen on the 
port: ${port}!`))

exports.app = app