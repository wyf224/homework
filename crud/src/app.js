const express = require('express')
const fs = require('fs')
const app = express()
const port = 3000


app.get('/tasks', (req, res) => fs.readFile('./Todo.json', 'utf-8', (err, data) => {
    if(err){
        res.status(500).send()
    } else {
        res.json(JSON.parse(data))
    }
}))



app.listen(port, () => console.log(`Our server has been setup,  and listen on the 
port: ${port}!`))
