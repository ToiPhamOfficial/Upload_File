module.exports.config = {
	name: "shortlink",
	version: "1.0.0",
	hasPermssion: 0,
	credits: "Mirai Team",
	description: "Rút gọn url của bạn",
	commandCategory: "other",
	usages: "[link]",
	cooldowns: 5,
	dependencies: {
		"bitly": ""
	},
	envConfig: {
		"bitlyAPI": ""
	}
};

module.exports.run = async ({ api, event, args }) => {
	return api.sendMessage("Link đã rút gọn: ", event.threadID, event.messageID);
}