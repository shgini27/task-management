import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * Method to add new card list
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/card-lists', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to change card list positions
   * @param positionChanges
   * @returns {Promise<unknown>}
   */
  changePositions (positionChanges) {
    return new Promise((resolve, reject) => {
      axios.post('/card-lists/positions', positionChanges).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
