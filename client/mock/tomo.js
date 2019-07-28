let count = 0

export default {
     'GET /api/text': (req,res) => {
      count = count + 1
      res.send({
          name: count
      })
     }
}
